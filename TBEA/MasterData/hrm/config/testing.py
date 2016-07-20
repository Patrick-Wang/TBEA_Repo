#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : testing.py
#  @ time : 2016/7/21 0:41
#  @ author : Patrick Wang

from .default import DefaultConfig


class TestingConfig(DefaultConfig):
    # App config
    TESTING = True

