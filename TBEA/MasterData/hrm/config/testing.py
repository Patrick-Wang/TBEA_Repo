#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : testing.py
#  @ time : 2016/7/21 0:41
#  @ author : Patrick Wang

from .default import DefaultConfig


class TestingConfig(DefaultConfig):
    # App config
    TESTING = True

    # sqlalchemy config
    SQLALCHEMY_DATABASE_URI = 'mysql://root:root@localhost/hrm'
    SQLALCHEMY_BINDS = {
        'eLink': 'mysql://root:root@localhost/elink',
        'hr': 'mysql://root:root@localhost/hr'
    }
    SQLALCHEMY_ECHO = True