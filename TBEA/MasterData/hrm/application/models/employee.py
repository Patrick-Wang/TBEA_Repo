#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : employee.py
#  @ time : 2016/7/21 14:26
#  @ author : Patrick Wang

from .. import db_elink, db_base, db_hr


class EmployeeELink(db_elink.Model):
    __bind_key__ = 'elink'
    __tablename__ = 'employee'

    id = db_elink.Column(db_elink.Integer, primary_key=True)
    shortname = db_elink.Column(db_elink.String(100), unique=True)
    username = db_elink.Column(db_elink.String(18), unique=True)
    fullname = db_elink.Column(db_elink.String(100))

    def __init__(self, shortname, username, fullname):
        self.shortname = shortname
        self.username = username
        self.fullname = fullname

    def __repr__(self):
        return '<EmployeeELink %r>' % self.username


class EmployeeHR(db_hr.Model):
    __bind_key__ = 'hr'
    __tablename__ = 'employee'

    id = db_hr.Column(db_hr.Integer, primary_key=True)
    shortname = db_hr.Column(db_hr.String(100), unique=True)
    username = db_hr.Column(db_hr.String(18), unique=True)
    fullname = db_hr.Column(db_hr.String(100))

    def __init__(self, shortname, username, fullname):
        self.shortname = shortname
        self.username = username
        self.fullname = fullname

    def __repr__(self):
        return '<EmployeeHR %r>' % self.username


class EmployeeBase(db_base.Model):
    __tablename__ = 'employee'

    id = db_base.Column(db_base.Integer, primary_key=True)
    shortname = db_base.Column(db_base.String(100), unique=True)
    username = db_base.Column(db_base.String(18), unique=True)
    fullname = db_base.Column(db_base.String(100))

    def __init__(self, shortname, username, fullname):
        self.shortname = shortname
        self.username = username
        self.fullname = fullname

    def __repr__(self):
        return '<EmployeeBase %r>' % self.username