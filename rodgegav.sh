#!/bin/bash

# Check if connected to Internet or not
ping -c 1 www.dit.ie &> /dev/null && echo  "Internet Status: Connected" > systemfile.txt || echo -e "Internet Status: Disconnected" > systemfile.txt

##display operating system name
operating_sys=$(uname -o) 
echo "Operating System: " $operating_sys  >> systemfile.txt

#check for ip address 
internalip=$(hostname -I)
echo "IP Address: " $internalip >> systemfile.txt

#uptime 
system_uptime=$(uptime | awk '{print $3,$4}' | cut -f1 -d,)
echo "System uptime" $system_uptime >> systemfile.txt

# Get the load average 
loadaverage=$(top -n 1 -b | grep "load average:" | awk '{print $10 $11 $12}') 
echo "The load average is " $loadaverage >> systemfile.txt


# free memory 
free_memory=$(free -mto | grep Mem | cut -f 2 -d '	') 
echo "Free memory: " $free_memory >> systemfile.txt 

dmesg 
