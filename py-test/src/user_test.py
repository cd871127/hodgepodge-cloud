import json
import os

import urllib3

import cipher_test

host = "172.28.0.51"


def register(user_info):
    key_id, public_key = cipher_test.get_public_key(0)
    user_info["passwordKeyId"] = key_id
    user_info["password"] = cipher_test.encode(public_key, user_info["password"])

    request = urllib3.PoolManager().connection_from_host(host=host)

    response = request.request("post", "/service-auth/user", body=json.dumps(user_info),
                               headers={'Content-Type': 'application/json'})

    if response.status != 200:
        print(response.status)
    else:
        print(response.data.decode("utf-8"))


def login(user_id, password):
    key_id, public_key = cipher_test.get_public_key()
    password = cipher_test.encode(public_key, password)
    request = urllib3.PoolManager().connection_from_host(host=host, port=80)
    response = request.request("POST", os.path.join("/service-auth/auth/", user_id, key_id), body=password,
                               headers={'Content-Type': 'application/json'})
    if response.status != 200:
        print(response.status)
    else:
        print(response.data.decode())


def modify_password(user_id, new_password, old_password):
    key_id, public_key = cipher_test.get_public_key()
    new_password = cipher_test.encode(public_key, new_password)
    old_password = cipher_test.encode(public_key, old_password)
    request = urllib3.PoolManager().connection_from_host(host=host, port=80)
    response = request.request("Patch", os.path.join("/service-auth/user/password/", user_id, ),
                               body=json.dumps({"newPasswordKeyId": key_id, "newPassword": new_password,
                                                "oldPassword": old_password}),
                               headers={'Content-Type': 'application/json'})
    if response.status != 200:
        print(response.status)
    else:
        print(response.data.decode())


# register()
if __name__ == '__main__':
    user_info = {
        "userId": "cd",
        "username": "a",
        "password": "cd",
        "phone": "123123",
        "eMail": "ggg"
    }
    register(user_info)
    # login(user_info["userId"], user_info["password"])
    # modify_password(user_info["userId"], "rrrrr", user_info["password"])
