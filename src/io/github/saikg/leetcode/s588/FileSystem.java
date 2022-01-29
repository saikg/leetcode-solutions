package io.github.saikg.leetcode.s588;

import java.util.*;

public class FileSystem {

    FileNode root;

    public FileSystem() {
        root = new FileNode("", FileType.DIRECTORY);
    }

    public List<String> ls(String path) {
        String[] splitPath = path.substring(1).split("/");
        FileNode node = root;

        for (String subDirectory : splitPath) {
            if (!node.directoryContents.containsKey(subDirectory)) {
                return Collections.emptyList();
            }
            node = node.directoryContents.get(subDirectory);
        }
        return new ArrayList<>(node.directoryContents.keySet());
    }

    public void mkdir(String path) {
        String[] splitPath = path.substring(1).split("/");
        FileNode node = root;
        for (String subDirectory : splitPath) {
            if (!node.directoryContents.containsKey(subDirectory)) {
                node.directoryContents.put(subDirectory, new FileNode(subDirectory, FileType.DIRECTORY));
            }
            node = node.directoryContents.get(subDirectory);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] splitPath = filePath.substring(1).split("/");

        FileNode node = root;
        for (String subDirectory : splitPath) {
            if (!node.directoryContents.containsKey(subDirectory)) {
                node.directoryContents.put(subDirectory, new FileNode(subDirectory, FileType.DIRECTORY));
            }
            node = node.directoryContents.get(subDirectory);
        }

        String fileName = splitPath[splitPath.length - 1];
        if (!node.directoryContents.containsKey(fileName)) {
            node.directoryContents.put(fileName, new FileNode(fileName, FileType.FILE));
        }
        node.directoryContents.get(fileName).fileContents += content;
    }

    public String readContentFromFile(String filePath) {
        int li = filePath.lastIndexOf('/');
        String[] splitPath = filePath.substring(1, li).split("/");
        String fileName = filePath.substring(li+1);

        FileNode node = root;
        for (String subDirectory : splitPath) {
            node = node.directoryContents.get(subDirectory);
        }
        return node.directoryContents.get(fileName).fileContents;
    }

    enum FileType {
        FILE,
        DIRECTORY;
    }

    static class FileNode {

        FileType type;

        String name;

        TreeMap<String, FileNode> directoryContents;

        String fileContents;

        FileNode(String name_, FileType type_) {
            name = name_;
            type = type_;
            directoryContents = new TreeMap<>();
            fileContents = "";
        }

        @Override
        public String toString() {
            String cv = (type == FileType.FILE) ? fileContents : directoryContents.toString();
            return "[" + name  + " : " + cv + "]";
        }
    }
}