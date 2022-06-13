import threading
from collections import Counter


def get_chunks(content, chunk_size=5):
    return [content[i:i + chunk_size] for i in range(0, len(content), chunk_size)]


def get_letter_frequency(word1, idx, results_collector):
    results_collector[idx] = Counter(word1)


def aggregate_letter_frequency(results_collector):
    final_counter = Counter()
    for counter in results_collector:
        final_counter += counter
    return final_counter


def compute_letter_frequency(content):
    chunks = get_chunks(content)
    jobs = [None] * len(chunks)
    results = [None] * len(chunks)
    for chunk_id in range(len(chunks)):
        t = threading.Thread(target=get_letter_frequency, args=(chunks[chunk_id], chunk_id, results,))
        jobs[chunk_id] = t
    [j.start() for j in jobs]
    [j.join() for j in jobs]
    return aggregate_letter_frequency(results)


def is_anagram(content1, content2):
    return compute_letter_frequency(content1) == compute_letter_frequency(content2)


if __name__ == '__main__':
    print(is_anagram("aaaaabbbccczijknll12333", "aaaaabbbccczijknll12333"))
    print(is_anagram("silent", "listen"))
    print(is_anagram("aaaa", "bbbb"))
