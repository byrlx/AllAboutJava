package com.lxjlib.git;

import com.lxjlib.util.Common;
import com.lxjlib.git.beans.Branch;
import com.lxjlib.git.beans.Tag;

import java.io.*;
import java.util.ArrayList;

/**
 * 该类用于解析代码库的的git相关信息, 例如branch,
 * tags等等
 * Created by douhua on 2/25/16.
 */
public class GitParser {
    private static final String REPO_PATH = "/Users/douhua/coohua/CooHuaClient/";
    private static final String GIT_DIR = ".git/";

    private String repoGitPath;
    private ArrayList<Branch> branches;  //记录branch和该branch的最新commit id
    private ArrayList<Tag> tags;

    public GitParser() {
        this(REPO_PATH);
    }

    public GitParser(String repoPath) {
        this.repoGitPath = repoPath + "/" + GIT_DIR;
    }

    private static final String REF_FILE_RELATIVE_PATH = "info/refs";

    /**
     * 获取每个branch的最新提交信息
     * <p>
     * 通过读取.git目录下的ref/heads/目录下的文件内容即可,
     * 文件名即branch名, 文件内容即该branch的最新提交id.
     * <p>
     * TODO:将这些信息放入到数据库中, 这样以后就可以通过检查有无新提交判断是否需要重新打包.
     */
    public void parseRefsHeads() {
        String parseDir = repoGitPath + "refs/heads/";

        File dir = new File(parseDir);
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        for (File file : files) {
            String branchName = file.getName();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String commitId = br.readLine();
                if (branches == null) {
                    branches = new ArrayList<>();
                }

                Branch branch = new Branch(branchName, commitId.substring(0, 10), System.currentTimeMillis());
                branches.add(branch);
                Common.print(branchName + ": " + commitId);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

        if (branches.size() > 0) {
            DbOperator.updateBranches(branches);
        }
    }


    /**
     * 获取tag信息
     * 通过解析 .git目录下的packed-refs
     */
    public void parseTag() {
        String tagFile = repoGitPath + "packed-refs";
        String searchStr = "refs/tags/";

        try {
            BufferedReader br = new BufferedReader(new FileReader(tagFile));
            String line;
            if (tags == null) {
                tags = new ArrayList<>();
            }

            while ((line = br.readLine()) != null) {
                int index = line.indexOf(searchStr);
                if (index == -1) continue;

                String tagName = line.substring(index + searchStr.length());
                String tagCI = (br.readLine()).substring(1, 11);
                Tag tag = new Tag(tagName, tagCI);
                tags.add(tag);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tags.size() > 0) {
            DbOperator.updateTags(tags);
        }
    }

    public static void test() {
        GitParser gitParser = new GitParser();
//        gitParser.parseRefsHeads();
        gitParser.parseTag();
    }
}
