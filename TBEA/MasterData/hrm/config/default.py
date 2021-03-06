#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : default.py
#  @ time : 2016/7/21 0:40
#  @ author : Patrick Wang

import os


class DefaultConfig(object):
    DEBUG = False
    TESTING = False
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'this word is hard to guess'

    # sqlalchemy config
    SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:root@localhost/hrm'
    SQLALCHEMY_BINDS = {
        'elink': 'mysql+pymysql://root:root@localhost/elink',
        'hr': 'mysql+pymysql://root:root@localhost/hr'
    }
    SQLALCHEMY_COMMIT_ON_TEARDOWN = True
    SQLALCHEMY_ECHO = False
