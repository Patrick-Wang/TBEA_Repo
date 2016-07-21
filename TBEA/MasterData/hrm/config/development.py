#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : development.py
#  @ time : 2016/7/21 0:40
#  @ author : Patrick Wang

from .default import DefaultConfig


class DevelopmentConfig(DefaultConfig):
    # App config
    DEBUG = True

    # sqlalchemy config
    SQLALCHEMY_DATABASE_URI = 'mysql://root:root@localhost/hrm'
    SQLALCHEMY_BINDS = {
        'eLink': 'mysql://root:root@localhost/elink',
        'hr': 'mysql://root:root@localhost/hr'
    }
    SQLALCHEMY_ECHO = True
