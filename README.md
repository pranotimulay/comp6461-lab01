##COMP 6461
##LAB ASSIGNMENT 1
##By Babita Bashal & Pranoti Mulay

#Purpose
- to implement the basic functionalities of cURL command line, the functionalities that are related to HTTP protocol.
- implement HTTP client library for GET and POST using Sockets

#Syntax
httpc (get|post) [-v] (-h "k:v")* [-d inline-data] [-f file] URL
1. Option -v enables a verbose output from the command-line. Verbosity could be useful
for testing and debugging stages where you need more information to do so. You
define the format of the output. However, you are expected to print all the status, and
its headers, then the contents of the response.
Comp 6461 – Fall 2020 - Lab Assignment # 1 Page 6
2. URL determines the targeted HTTP server. It could contain parameters of the HTTP
operation. For example, the URL 'https://www.google.ca/?q=hello+world' includes the
parameter q with "hello world" value.
3. To pass the headers value to your HTTP operation, you could use -h option. The latter
means setting the header of the request in the format "key: value." Notice that; you can
have multiple headers by having the -h option before each header parameter.
4. -d gives the user the possibility to associate the body of the HTTP Request with the
inline data, meaning a set of characters for standard input.
5. Similarly, to -d, -f associate the body of the HTTP Request with the data from a given
file.
6. get/post options are used to execute GET/POST requests respectively. post should
have either -d or -f but not both. However, get option should not be used with the
options -d or -f.
