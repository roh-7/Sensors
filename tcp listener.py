import sys
import socket
import math
import cv2

TCP_IP = "192.168.1.36"
TCP_PORT= 9001

sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
sock.bind((TCP_IP,TCP_PORT))
sock.listen(True)
print 'starting while loop'
while True:
        conn,addr=sock.accept()
        data = conn.recv(10000)
        print data
        cv2.waitKey(5) 
sock.close()        
conn.close()

