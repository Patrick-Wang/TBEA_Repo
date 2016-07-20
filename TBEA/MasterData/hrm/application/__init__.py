#!/usr/bin/env python
# coding=utf-8

from flask import Flask
from flask_bootstrap import Bootstrap
from config import load_config

bootstrap = Bootstrap()


def create_app():
    app = Flask(__name__)
    bootstrap.init_app(app)

    # Load config
    config = load_config()
    app.config.from_object(config)

    from application.views.hrm import blueprint as hrm_blueprint
    app.register_blueprint(hrm_blueprint)

    return app
