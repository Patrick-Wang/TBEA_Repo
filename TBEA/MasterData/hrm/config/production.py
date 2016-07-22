#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : production.py
#  @ time : 2016/7/21 0:41
#  @ author : Patrick Wang

from .default import DefaultConfig


class ProductionConfig(DefaultConfig):
    # sqlalchemy config
    SQLALCHEMY_DATABASE_URI = 'mysql://root:root@localhost/hrm'
    SQLALCHEMY_BINDS = {
        'eLink': 'mysql://root:root@localhost/elink',
        'hr': 'mysql://root:root@localhost/hr'
    }



