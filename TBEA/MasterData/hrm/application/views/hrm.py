#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : hrm.py
#  @ time : 2016/7/19 14:02
#  @ author : Patrick Wang

from flask import Blueprint, render_template
from ..forms.hrm import UserIdForm

blueprint = Blueprint('hrm_blueprint', __name__)


@blueprint.route('/hrm', methods=['GET', 'POST'])
def hrm_id():
    hr_id = None
    user_id_form = UserIdForm()
    if user_id_form.validate_on_submit():
        hr_id = user_id_form.user_id.data
        user_id_form.user_id.data = ''
    return render_template('hrm.html', user_id_form=user_id_form, hr_id=hr_id)
