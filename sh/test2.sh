#!/bin/bash

# 显示硬盘使用情况
df -h

# 显示内存使用情况
free -h

# 显示CPU使用情况
top -n 1 -b | grep "Cpu(s)"