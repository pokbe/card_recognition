import cv2
import os
import shutil
THRESH=80
SEG_RADIO=1.3
SEG_WIDTH=20
imgo=cv2.imread('image.jpg',0)
img=cv2.resize(imgo,(480,640),fx=1,fy=1)
ret1, th1 = cv2.threshold(img , THRESH, 255, cv2.THRESH_BINARY)
th1=255-th1
cv2.imshow('th1', th1)
cv2.waitKey(0)
elemt = cv2.getStructuringElement(cv2.MORPH_CROSS,(1,3))
kernel = cv2.getStructuringElement(cv2.MORPH_CROSS,(9,3))
#kernel = np.ones((5, 5), np.uint8)
th2 = cv2.erode(th1, elemt, iterations = 1)
dilation = cv2.dilate(th2, kernel, iterations = 2)
#dilation = cv2.dilate(th3, kernel, iterations = 1)
cv2.imshow('dilation', dilation)
cv2.waitKey(0)
contours, hierarchy = cv2.findContours(dilation, cv2.RETR_CCOMP, cv2.CHAIN_APPROX_SIMPLE)
x1 = 0; y1 = 0; w1 = 0; h1 = 0; max_ratio = 0
for cnt in contours:
    x,y,w,h = cv2.boundingRect(cnt)
    delt = w*1.0 / h
    if max_ratio < delt: 
        max_ratio = delt
        x1 = x
        y1 = y
        w1 = w
        h1 = h

index=[]
rect={}
origin = img[y1:h1+y1,x1:w1+x1]
cv2.imshow('origin', origin)
cv2.waitKey(0)
ret, th = cv2.threshold(origin, 75, 255, cv2.THRESH_BINARY)
th=255-th
th1=1*th
contours, hierarchy = cv2.findContours(th, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
for cnt in contours:
        x, y, w, h = cv2.boundingRect(cnt)
        tmp =1*cv2.resize(th1[abs(y-3):h+y+2, abs(x-3):w+x+2], (32, 32), fx=1, fy=1)
        index.append(x)
        rect[x] = tmp
        cv2.rectangle(origin, (x-3, y-3), (w+x+5, h+y+2), (0, 255, 0), 2)
     #   else:
      #      tmp = cv2.resize(th1[abs(y-3):h+y+2, abs(x-2):int(x+w*0.5)], (20, 20), fx=1, fy=1)
       #     index.append(x)
        #    rect[x] = tmp
         #   tmp1 = cv2.resize(th1[abs(y-3):h+y+2, abs(int(w*0.5)+x-3):w+x+2], (20, 20), fx=1, fy=1)
          #  index.append(int(w*0.5)+x)
           # rect[int(w*0.5)+x] = tmp1
            #cv2.rectangle(origin, (x+int(w*0.5)+x-3, y-3), (int(w*0.5)+x+5, h+y+2), (0, 255, 0), 2)
            #index.sort()
i=0
cv2.imshow('origin', origin)
cv2.waitKey(0)
index.sort()
if os.path.exists("graph"):
  shutil.rmtree("graph")
os.mkdir("graph")
for Iter in index:
        cv2.imwrite('graph\\'+str(i)+'.jpg', rect[Iter])
        i += 1
if os.path.exists("test"):
  shutil.rmtree("test")
os.mkdir("test")
