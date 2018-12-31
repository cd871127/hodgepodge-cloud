import pymysql


class MySql(object):

    def __init__(self, host="172.28.0.4", user="appopr", password="123456", database="service_cipher"
                 , use_unicode=True, charset='utf8', cursorclass=pymysql.cursors.DictCursor):
        self.__host = host
        self.__user = user
        self.__password = password
        self.__database = database
        self.__use_unicode = use_unicode
        self.__charset = charset
        self.__cursorclass = cursorclass
        self.__db = pymysql.connect(host=self.__host, user=self.__user, password=self.__password,
                                    database=self.__database
                                    , use_unicode=self.__use_unicode, charset=self.__charset,
                                    cursorclass=self.__cursorclass)

    def insert(self):
        cursor = self.__db.cursor()
        cursor.execute("select * from t66y.FORUM_POSTS")
        data = cursor.fetchall()
        cursor.close()
        return data

    def update(self, sql):
        self.__write(sql)

    def delete(self, sql):
        self.__write(sql)

    def select(self, sql):
        cursor = self.__db.cursor()
        cursor.execute(sql)
        data = cursor.fetchall()
        cursor.close()
        return data

    def __write(self, sql):
        cursor = self.__db.cursor()
        res = cursor.execute(sql)
        self.__db.commit()
        cursor.close()
        return res

    def close(self):
        self.__db.close()
