#!/bin/bash

# 提示用户输入要搜索的内容
echo "请输入要搜索的内容："
read search_content

# 提示用户输入要搜索的文件路径
echo "请输入要搜索的文件路径："
read file_path

# 搜索指定内容并将结果保存到新文件中
grep -n "$search_content" "$file_path" > search_results.txt

# 将结果输出到控制台
echo "搜索结果："
cat search_results.txt