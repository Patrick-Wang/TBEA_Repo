#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : hrm.py
#  @ time : 2016/7/19 14:02
#  @ author : Patrick Wang

from flask import Blueprint, render_template, redirect, url_for, session
from ..forms.hrm import UserIdForm
from ..models.employee import EmployeeBase, EmployeeELink, EmployeeHR
from .. import db_elink, db_hr, db_base

blueprint = Blueprint('hrm_blueprint', __name__)


@blueprint.route('/hrm', methods=['GET', 'POST'])
def hrm_id():
    user_id_form = UserIdForm()
    if user_id_form.validate_on_submit():
        session['hr_id'] = user_id_form.user_id.data
        return redirect(url_for('hrm_blueprint.hrm_id'))
    return render_template('hrm.html', hr_id=session.get('hr_id'), user_id_form=user_id_form)


@blueprint.route('/hrm/run')
def hrm_run():
    user_elink = EmployeeELink('wangxin11', '220702198701061411', '王鑫')
    user_hr = EmployeeHR('wangxin11', '220702198701061411', '王鑫')
    user_hr2 = EmployeeHR('wangxin12', '220702198701061412', '王鑫1')
    user_base = EmployeeBase('wangxin11', '220702198701061411', '王鑫')
    user_base1 = EmployeeBase('wangxin12', '220702198701061412', '王鑫1')
    user_base2 = EmployeeBase('wangxin13', '220702198701061413', '王鑫2')
    user_base3 = EmployeeBase('wangxin14', '220702198701061414', '王鑫3')

    db_base.session.add(user_base)
    db_base.session.add(user_base1)
    db_base.session.add(user_base2)
    db_base.session.add(user_base3)
    db_base.session.commit()

    db_hr.session.add(user_hr)
    db_hr.session.add(user_hr2)
    db_hr.session.commit()

    db_elink.session.add(user_elink)
    db_elink.session.commit()

    return '<h1>haha</h1>'