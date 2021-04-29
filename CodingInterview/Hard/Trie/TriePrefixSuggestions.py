
"""


"""
from CodingInterview.Hard.Trie.Model.TrieNodeModel import TrieNode


def insert_word(root: TrieNode, word: str):
    crawler = root
    for c in word:
        ascii_code = ord(c)
        if crawler.children[ascii_code] is None:
            crawler.children[ascii_code] = TrieNode()
        crawler = crawler.children[ascii_code]
    crawler.is_word_ending = True


def retrieve_all_words(root: TrieNode):
    def retrieve_all_helper(crawler, acc, words):
        if crawler is None:
            return
        if crawler.is_word_ending:
            words.append(acc)
        for i in range(len(crawler.children)):
            retrieve_all_helper(crawler.children[i], acc + chr(i), words)

    words = []
    retrieve_all_helper(root, '', words)
    return words


def search_prefix(root: TrieNode, word: str):
    crawler = root
    for c in word:
        ascii_code = ord(c)
        if crawler.children[ascii_code] is None:
            return None
        crawler = crawler.children[ascii_code]
    return crawler


def typeahead_suggestions(root: TrieNode, prefix: str):
    suffixes = retrieve_all_words(search_prefix(root, prefix))
    return [prefix + suffix for suffix in suffixes]


if __name__ == '__main__':
    root = TrieNode()
    insert_word(root, 'freeze')
    insert_word(root, 'freeze is a programmer')
    insert_word(root, 'freezer')
    insert_word(root, 'freezing')
    insert_word(root, 'free')
    insert_word(root, 'trie data structure')
    print(retrieve_all_words(root))
    print(typeahead_suggestions(root, 'freez'))
