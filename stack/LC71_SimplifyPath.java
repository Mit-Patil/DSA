class Solution {
    public String simplifyPath(String path) {
        String[] folderName = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        for (String s : folderName) {
            if (s.isEmpty() || s.equals(".")) continue;
            else if (s.equals("..")) {
                if (!stack.isEmpty()) stack.removeLast();
            } else stack.addLast(s);
        }

        return "/" + String.join("/", stack);
    }
}