#!/usr/bin/env python
# coding=utf-8

import os
from flask import Flask
from flask_bootstrap import Bootstrap

bootstrap = Bootstrap()


def create_app():
    app = Flask(__name__)
    bootstrap.init_app(app)

    app.config['SECRET_KEY'] = 'hard to guess string'

    from application.views.hrm import blueprint as hrm_blueprint
    app.register_blueprint(hrm_blueprint)

    return app
