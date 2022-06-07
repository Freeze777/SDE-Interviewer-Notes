import threading
from collections import Counter


def chunk(word, chunk_size=5):
    return [word[i:i + chunk_size] for i in range(0, len(word), chunk_size)]


def get_letter_count(word1, idx, results_collector):
    results_collector[idx] = Counter(word1)


def aggregate_counts(results_collector):
    final_wc = Counter()
    for wc in results_collector:
        final_wc += wc
    return final_wc


def compute(content):
    chunks = chunk(content)
    jobs = [None] * len(chunks)
    results = [None] * len(chunks)
    for chunk_id in range(len(chunks)):
        t = threading.Thread(target=get_letter_count, args=(chunks[chunk_id], chunk_id, results,))
        jobs[chunk_id] = t
    [j.start() for j in jobs]
    [j.join() for j in jobs]
    return aggregate_counts(results)


def is_anagram(content1, content2):
    return compute(content1) == compute(content2)


if __name__ == '__main__':
    print(is_anagram("aaaaabbbccczijknll12333", "aaaaabbbccczijknll12333"))
    print(is_anagram("silent", "listen"))
    print(is_anagram("aaaa", "bbbb"))
