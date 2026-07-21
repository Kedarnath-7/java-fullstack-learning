print("Hello, World!")

a = 10
b = "Hi"
print(a, b)
a, b = b, a
print(a, b)

c = int("123")
print(c)

if(c > 100):
    print("c is greater than 100")
else:
    print("c is less than or equal to 100")
    
for i in range(5):
    print(i)
    
while(b > 0):
    print(b)
    b -= 1
    
print(type(a))
print(type(b))

    
print(isinstance(a, int))
print(isinstance(b, str))

