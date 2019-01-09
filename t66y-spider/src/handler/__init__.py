from bs4 import BeautifulSoup

from log import LoggerObject


class Handler(LoggerObject):

    def __init__(self, downloader):
        super().__init__("handler")
        self.__downloader = downloader

    def handle(self, url):
        html = self.__downloader.get(url)
        self.__handle_html(html)

    def __handle_html(self, html):
        pass


class PageHandler(Handler):

    def __handle_html(self, html):
        topic_list = list()
        soup = BeautifulSoup(html, "html.parser")
        table = soup.find(name="table", id="ajaxtable")
        if table is None:
            self.logger.error("handle page error: cannot find table\n%s" % html)
            return topic_list
        # 判断是否首页
        try:
            topic_start_flag = table.find_all(name="tr", attrs={"class": "tr2"})
            topic_start_flag = topic_start_flag[len(topic_start_flag) - 1]
            for tr in topic_start_flag.find_next_siblings(name="tr", attrs={"class": "tr3 t_one tac"}):
                a = tr.find(name="h3").a
                topic_list.append({"url": a['href'], "title": a.text})
        except Exception as e:
            self.logger.error("handle page error:\n%s" % html)
        return topic_list


class TopicHandler(Handler):

    def __handle_html(self, html):
        topic = dict()
        soup = BeautifulSoup(html, "html.parser")
        if soup.select(".tpc_content img") is None:
            topic["status"] = "1"
            self.logger.error("handle topic error:\n%s" % html)
            return topic
        try:
            get_image = lambda tag: list(
                map(lambda image: image[tag].replace(".th", ""),
                    filter(lambda image: image.get(tag) is not None and (
                            image[tag].endswith(".jpg") or image[tag].endswith(".JPG") or image[tag].endswith(
                        ".jpeg") or image[tag].endswith(".png")),
                           soup.select(".tpc_content img"))))
            topic["images"] = list(set(get_image("data-src")) | set(get_image("src")))
            topic["torrent_links"] = list(
                map(lambda a: a.text, filter(lambda a: "hash=" in a.text, soup.select(".tpc_content a"))))
            if topic["images"] is None or len(topic["images"]) == 0:
                topic["status"] = "2"  # image error
            if topic["torrent_links"] is None or len(topic["torrent_links"]) == 0:
                topic["status"] = "3"  # torrent_error
        except Exception as e:
            self.logger.error("handle topic error:\n%s" % html)
            topic["status"] = "1"
        return topic


class HtmlResponseHandler(LoggerObject):

    def handle(self, response):
        if response is None:
            self.logger.error("response is None")
            return None
        if response.status != 200:
            self.logger.error("response code: %s" % str(response.status))
            return None
        return response.data.decode("gbk", "ignore")
