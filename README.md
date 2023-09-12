# kotlin_training_whois
Doing different projects to learn more about Kotlin.

In this project, I have a website that can be browsed at http://localhost:8080/.

This is a webpage where you can enter a domain name to request the whois information. The request is sent to an API `/api/getWhoIs?domainName=${domainName}`.

The API then checks if the host machine is a Windows machine or not. The reason for this is that the whois command is not native to Windows, so the executable that is bundled with this project is used to give that functionality. Mac/Linux machines already have whois, so it's not required to have a whois executable for those OSes.

This command (`Runtime.getRuntime().exec(command`) is used to run the whois command on the host machine.
