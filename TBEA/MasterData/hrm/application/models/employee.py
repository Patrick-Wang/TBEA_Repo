#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : employee.py
#  @ time : 2016/7/21 14:26
#  @ author : Patrick Wang

from .. import db


class EmployeeELink(db.Model):
    __bind_key__ = 'elink'
    __tablename__ = 'employeeELink'

    id = db.Column(db.Integer, primary_key=True)
    shortname = db.Column(db.String(100), unique=True)
    username = db.Column(db.String(18), unique=True)
    fullname = db.Column(db.String(100))


class EmployeeHR(db.Model):
    __bind_key__ = 'hr'
    __tablename__ = 'employeeHR'

    id = db.Column(db.Integer, primary_key=True)
    shortname = db.Column(db.String(100), unique=True)
    username = db.Column(db.String(18), unique=True)
    fullname = db.Column(db.String(100))


class EmployeeBase(db.Model):
    __tablename__ = 'employee'

    id = db.Column(db.Integer, primary_key=True)
    shortname = db.Column(db.String(100), unique=True)
    username = db.Column(db.String(18), unique=True)
    fullname = db.Column(db.String(100))

