import java.util.Set;
import java.util.HashSet;

class Solution {

    public int solution(String message, int[][] spoiler_ranges) {
        boolean[] spoiler = new boolean[message.length()];

        // 1. 스포일러 범위 마킹
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                spoiler[i] = true;
            }
        }

        Set<String> normalWords = new HashSet<>();
        Set<String> spoilerWords = new HashSet<>();

        int start = 0;

        // 2. message를 단어 단위로 순회
        while (start < message.length()) {

            int end = start;
            boolean containsSpoiler = false;

            while (end < message.length() && message.charAt(end) != ' ') {
                if (spoiler[end]) {
                    containsSpoiler = true;
                }
                end++;
            }

            String word = message.substring(start, end);

            if (containsSpoiler) {
                spoilerWords.add(word);
            } else {
                normalWords.add(word);
            }

            start = end + 1;
        }

        // 3. 평문으로 이미 등장한 단어는 스포일러가 아님
        spoilerWords.removeAll(normalWords);

        return spoilerWords.size();
    }
}