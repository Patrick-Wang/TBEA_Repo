#!/usr/bin/env python
# coding=utf-8

from flask import Flask
from flask_bootstrap import Bootstrap
from flask_sqlalchemy import SQLAlchemy
from config import load_config

bootstrap = Bootstrap()
db_elink = SQLAlchemy()
db_hr = SQLAlchemy()
db_base = SQLAlchemy()


def create_app():
    # init Flask
    app = Flask(__name__)

    # init ext of Flask
    bootstrap.init_app(app)

    db_elink.init_app(app)
    db_hr.init_app(app)
    db_base.init_app(app)

    # Load config
    config = load_config()
    app.config.from_object(config)

    from application.views.hrm import blueprint as hrm_blueprint
    app.register_blueprint(hrm_blueprint)

    return app
