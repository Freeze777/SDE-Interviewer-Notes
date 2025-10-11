package interviews;

import java.util.*;


class AuthContext {
    public final String userId;

    public AuthContext(String userId) {
        this.userId = userId;
    }
}

interface DesignService {
    String createDesign(AuthContext ctx, String designContent);

    String getDesign(AuthContext ctx, String designId);

    List<String> findDesigns(AuthContext ctx);

    void shareDesign(AuthContext ctx, String designId, String targetUserId);
}

/**
 * 1 user create multiple design.
 * internal memory datastructure
 * design id - string
 * authorization
 * multiple concurrent requests.
 * number of designs -> fits in memory
 */

class DesignContent {
    String id;
    String content;

    public DesignContent(String content) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }
}

class SharedContent {
    String sharedId;
    String ownerId;

    public SharedContent(String ownerId, String sharedId) {
        this.ownerId = ownerId;
        this.sharedId = sharedId;
    }
}

class DesignServiceImpl implements DesignService {
    final HashMap<String, HashMap<String, DesignContent>> contentStore = new HashMap<>();
    final HashMap<String, List<SharedContent>> sharedContentStore = new HashMap<>();


    @Override
    public String createDesign(AuthContext ctx, String designContent) {
        if (ctx.userId == null) {
            throw new IllegalArgumentException("null userid");
        }
        if (designContent != null) {
            if (!contentStore.containsKey(ctx.userId)) {
                contentStore.put(ctx.userId, new HashMap<>());
            }
            Map<String, DesignContent> userMap = contentStore.get(ctx.userId);// O(1)
            DesignContent design = new DesignContent(designContent);
            userMap.put(design.id, design); // O(1)
            return design.id;
        }
        throw new IllegalArgumentException("null designContent");
    }


    @Override
    public String getDesign(AuthContext ctx, String designId) {
        if (ctx.userId == null) {
            throw new IllegalArgumentException("null userid");
        }
        Map<String, DesignContent> userMap = contentStore.get(ctx.userId);// O(1)
        if (userMap.containsKey(designId)) {
            return userMap.get(designId).content;// O(1)
        } else if (sharedContentStore.containsKey(ctx.userId)) {
            var list = sharedContentStore.get(ctx.userId).stream().filter(sc -> Objects.equals(sc.sharedId, designId)).toList(); // linear search get SharedContent matches design
            if (list.isEmpty()) throw new IllegalStateException("user has no permission");
            return contentStore.get(list.get(0).ownerId).get(designId).content;
        } else {
            throw new IllegalStateException("user has no permission");
        }
    }

    @Override
    public List<String> findDesigns(AuthContext ctx) {
        // validations
        List<String> results = new ArrayList<>();
        if (contentStore.containsKey(ctx.userId)) {
            results.addAll(contentStore.get(ctx.userId).keySet());
        }
        if (sharedContentStore.containsKey(ctx.userId)) {
            results.addAll(sharedContentStore.get(ctx.userId).stream().map(sc -> sc.sharedId).toList());
        }

        return results;
    }

    @Override
    public void shareDesign(AuthContext ctx, String designId, String targetUserId) {
        //validations
        if (sharedContentStore.containsKey(targetUserId)) {
            sharedContentStore.get(targetUserId).add(new SharedContent(ctx.userId, designId));
        } else {
            sharedContentStore.put(targetUserId, Collections.singletonList(new SharedContent(ctx.userId, designId)));
        }
    }
}
