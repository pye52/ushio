package com.kanade.ushio.adapter

import android.text.TextUtils
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kanade.ushio.R
import com.kanade.ushio.entity.Staff
import com.kanade.ushio.utils.GlideApp
import com.kanade.ushio.utils.strFilter

class SubjectStaffAdapter(datas: List<Staff>) : BaseQuickAdapter<Staff, BaseViewHolder>(R.layout.rv_item_subject_detail_crt, datas) {
    override fun convert(helper: BaseViewHolder, item: Staff) {
        val info = StringBuilder("")
        item.roleName?.let {
            info.addString2Builder("", it, false)
        }
        item.jobs?.forEach {
            info.addString2Builder("", it, false)
        }
        item.info?.let {
            it.gender?.let { gender ->
                info.addString2Builder("性别", gender, true)
            }
            it.birth?.let { birth ->
                info.addString2Builder("生日", birth, true)
            }
        }

        val img = helper.setText(R.id.crt_name, strFilter(item.nameCn, item.name, 12))
                .setText(R.id.crt_info, info.toString())
                .setVisible(R.id.crt_cv_name, false)
                .getView<ImageView>(R.id.crt_img)
        if (item.images != null) {
            GlideApp.with(mContext)
                    .load(item.images?.getImageC2L())
                    .placeholder(R.drawable.img_on_load)
                    .error(R.drawable.img_on_error)
                    .into(img)
        }
    }

    private fun StringBuilder.addString2Builder(pre: String, str: String, isAppendEnd: Boolean): StringBuilder {
        if (!TextUtils.isEmpty(str)) {
            if (isAppendEnd) this.append(" \\ ")
            if (!TextUtils.isEmpty(pre)) this.append("$pre ")
            this.append("$str ")
        }
        return this
    }
}