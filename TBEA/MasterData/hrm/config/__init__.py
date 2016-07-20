#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : __init__.py
#  @ time : 2016/7/21 0:40
#  @ author : Patrick Wang

import os


def load_config():
    """加载配置项"""
    mode = os.environ.get('FLASK_MODE')

    try:
        if mode == 'PRODUCTION':
            from .production import ProductionConfig
            return ProductionConfig
        elif mode == 'TESTING':
            from .testing import TestingConfig
            return TestingConfig
        else:
            from .development import DevelopmentConfig
            return DevelopmentConfig
    except ImportError:
        from .default import DefaultConfig
        return DefaultConfig
