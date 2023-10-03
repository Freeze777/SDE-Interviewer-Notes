package interviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 1000 videos approx
 * no ttls
 * no thready safety
 * videoId uuid
 * don't use java.utils
 */
interface VideoCache {
    void set(String videoId, String videoContent);

    String get(String videoId);
}

class Video {
    String id;
    String content;
    Video next;

    public Video(String id, String content) {
        this.id = id;
        this.content = content;
    }
}

class VideoCacheImpl implements VideoCache {

    private final int MAX_SIZE;
    private final Video[] videos;

    public VideoCacheImpl(int capacity) {
        MAX_SIZE = capacity;
        videos = new Video[MAX_SIZE];
    }

    @Override
    public void set(String videoId, String videoContent) {
        var video = new Video(videoId, videoContent);
        var index = getBucketIndex(videoId);
        if (videos[index] != null) {
            var head = videos[index];
            while (head.next != null) {
                if (head.id.equals(videoId)) break;
                head = head.next;
            }
            if (head.id.equals(videoId)) {
                head.content = videoContent;
            } else {
                head.next = video;
            }
        } else {
            videos[index] = video;
        }
    }

    @Override
    public String get(String videoId) {
        int index = getBucketIndex(videoId);
        var video = videos[index];
        if (video == null) return null;
        var head = video;
        while (head != null) {
            if (head.id.equals(videoId)) return head.content;
            head = head.next;
        }
        return null;
    }

    private int getBucketIndex(String videoId) {
        int bucketIndex = (videoId.hashCode()) % MAX_SIZE;
        bucketIndex = bucketIndex >= 0 ? bucketIndex : (bucketIndex + MAX_SIZE) % MAX_SIZE;
        return bucketIndex;
    }
}

public class CanvaFinalInterviewTest {
    @Test
    public void testBasic() {
        VideoCache cache = new VideoCacheImpl(1000);
        cache.set("ahgfhjagfh-id", "ajdhajkfasfklsajklfshqkfn-content");
        assertEquals(cache.get("ahgfhjagfh-id"), "ajdhajkfasfklsajklfshqkfn-content");

        assertNull(cache.get("random-id"));

        cache.set("ahgfhjagfh-id", "lkjklhjkadjhgfhjaada-content");
        assertEquals(cache.get("ahgfhjagfh-id"), "lkjklhjkadjhgfhjaada-content");
    }

    @Test
    public void testCollisions() {
        VideoCache cache = new VideoCacheImpl(1);
        cache.set("ahgfhjagfh-id", "ajdhajkfasfklsajklfshqkfn-content");
        cache.set("bbhhhhhh-id", "bbhhhhhh-content");
        cache.set("cdfgs-id", "cdfgs-content");
        assertEquals(cache.get("ahgfhjagfh-id"), "ajdhajkfasfklsajklfshqkfn-content");
        assertEquals(cache.get("bbhhhhhh-id"), "bbhhhhhh-content");
        assertEquals(cache.get("cdfgs-id"), "cdfgs-content");
    }
}
