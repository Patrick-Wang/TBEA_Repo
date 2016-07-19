#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : manage.py
#  @ time : 2016/7/19 15:39
#  @ author : Patrick Wang

from application import create_app
from flask_script import Manager

app = create_app()
manager = Manager(app)

if __name__ == '__main__':
    manager.run()
