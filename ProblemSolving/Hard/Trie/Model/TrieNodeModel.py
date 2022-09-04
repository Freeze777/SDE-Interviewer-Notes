from typing import List


class TrieNode:
    ALPHABET_SIZE: int = 256

    def __init__(self):
        self.is_word_ending: bool = False
        self.children: List[TrieNode] = [None] * self.ALPHABET_SIZE
