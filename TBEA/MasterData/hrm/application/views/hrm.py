#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : hrm.py
#  @ time : 2016/7/19 14:02
#  @ author : Patrick Wang

from flask import Blueprint, render_template

blueprint = Blueprint('hrm_blueprint', __name__)


@blueprint.route('/hrm/<hr_id>')
def hrm_id(hr_id):
    return render_template('hrm.html', hr_id=hr_id)
