from websocket import create_connection
ws = create_connection("ws://127.0.0.1:8112/")
print "Sending 'Hello, World'..."
ws.send("Hello, World")
print "Sent"
print "Reeiving..."
result =  ws.recv()
print "Received '%s'" % result
ws.close()
