package ru.adept.extensions.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_message.view.*
import ru.zoo.R

//
//import android.app.Activity
//import android.app.TimePickerDialog
//import android.content.Context
//import android.util.Log
//import android.view.Gravity
//import android.view.View
//import android.view.WindowManager
//import android.widget.ImageView
//import androidx.appcompat.app.AlertDialog
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.bumptech.glide.Glide
//import com.github.sundeepk.compactcalendarview.CompactCalendarView
//import kotlinx.android.synthetic.main.calendar_filter_period.view.*
//import kotlinx.android.synthetic.main.dialog_attachment.view.*
//import kotlinx.android.synthetic.main.dialog_attachment.view.button_cancel_download
//import kotlinx.android.synthetic.main.dialog_attachment.view.upload_from_docs
//import kotlinx.android.synthetic.main.dialog_attachment.view.upload_from_gallery
//import kotlinx.android.synthetic.main.dialog_attachment.view.upload_photo
//import kotlinx.android.synthetic.main.dialog_attachment_with_system.view.*
//import kotlinx.android.synthetic.main.dialog_confirm_delete.view.*
//import kotlinx.android.synthetic.main.dialog_confirm_delete.view.button_cancel
//import kotlinx.android.synthetic.main.dialog_confirm_delete.view.button_ok
//import kotlinx.android.synthetic.main.dialog_context_menu_documents.view.*
//import kotlinx.android.synthetic.main.dialog_show_image.view.*
//import ru.adept.R
//import ru.adept.data.models.Attachment
//import ru.adept.data.models.Document
//import ru.adept.extensions.view.*
//import ru.adept.presentation.base.activities.attributesDocuments.DocumentAttributesActivity
//import ru.adept.presentation.documents.list.DocumentsRepository
//import java.text.SimpleDateFormat
//import java.util.*
//

fun dialogError(
    activity: Activity,
    context: Context,
    message:String
): AlertDialog {
    val dialogBuilder = AlertDialog.Builder(context)
    val inflater = activity.layoutInflater
    val dialogView = inflater.inflate(R.layout.dialog_message, null)
    dialogBuilder.setView(dialogView)
    val dDialog = dialogBuilder.create()
    dDialog.setCancelable(true)
    val window = dDialog.window
    val wlp = window!!.attributes
    wlp.gravity = Gravity.CENTER
    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
    window.attributes = wlp
    dialogView.error_message.text = message
    dialogView.button_ok.setOnClickListener {
        dDialog.dismiss()
    }
    dDialog.setOnCancelListener {
        dDialog.dismiss()
    }
    dDialog.show()
    return dDialog
}
//fun dialogDeleteConfirm(
//    activity: Activity,
//    context: Context,
//    title: String,
//    subTitle: String,
//    onDelete: () -> Unit
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_confirm_delete, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setCancelable(true)
//    val window = dDialog.window
//    val wlp = window!!.attributes
//    wlp.gravity = Gravity.CENTER
//    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    window.attributes = wlp
//    dialogView.title.text = title
//    dialogView.subTitle.text = subTitle
//    dialogView.button_cancel.setOnClickListener {
//        dDialog.dismiss()
//    }
//    dialogView.button_ok.setOnClickListener {
//        dDialog.dismiss()
//        onDelete.invoke()
//    }
//    dDialog.show()
//    return dDialog
//}
//fun dialogEditConfirm(
//    activity: Activity,
//    context: Context,
//    title: String,
//    subTitle: String,
//    onEdit: () -> Unit,
//    onCancel: () -> Unit
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_confirm_edit, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setCancelable(true)
//    val window = dDialog.window
//    val wlp = window!!.attributes
//    wlp.gravity = Gravity.CENTER
//    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    window.attributes = wlp
//    dialogView.title.text = title
//    dialogView.subTitle.text = subTitle
//    dialogView.button_cancel.setOnClickListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//    dialogView.button_ok.setOnClickListener {
//        dDialog.dismiss()
//        onEdit.invoke()
//    }
//    dDialog.show()
//    return dDialog
//}
//fun dialogAttachment(
//    activity: Activity,
//    context: Context,
//    onAddDoc: () -> Unit,
//    onAddFromGallery: () -> Unit,
//    onAddFromCamera: () -> Unit,
//    onCreateFolder: () -> Unit,
//    onCancel: () -> Unit
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_attachment, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setCancelable(true)
//    val window = dDialog.window
//    val wlp = window!!.attributes
//    wlp.gravity = Gravity.BOTTOM
//    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    window.attributes = wlp
//    dialogView.upload_from_gallery.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromGallery.invoke()
//    }
//
//    dialogView.upload_from_docs.setOnClickListener {
//        dDialog.dismiss()
//        onAddDoc.invoke()
//    }
//
//    dialogView.create_folder.setOnClickListener {
//        dDialog.dismiss()
//        onCreateFolder.invoke()
//    }
//
//    dialogView.upload_photo.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromCamera.invoke()
//    }
//
//    dialogView.button_cancel_download.setOnClickListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//
//    dDialog.setOnCancelListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//    dDialog.show()
//    return dDialog
//}
//
//fun dialogAttachmentPhoto(
//    activity: Activity,
//    context: Context,
//    onAddFromGallery: () -> Unit,
//    onAddFromCamera: () -> Unit,
//    onCancel: () -> Unit
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_attachment, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setCancelable(true)
//    val window = dDialog.window
//    val wlp = window!!.attributes
//    wlp.gravity = Gravity.BOTTOM
//    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    window.attributes = wlp
//    dialogView.upload_from_gallery.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromGallery.invoke()
//    }
//
//    dialogView.upload_from_docs.gone()
//
//    dialogView.create_folder.gone()
//
//    dialogView.upload_photo.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromCamera.invoke()
//    }
//
//    dialogView.button_cancel_download.setOnClickListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//
//    dDialog.setOnCancelListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//    dDialog.show()
//    return dDialog
//}
//fun dialogAttachmentWithSystem(
//    activity: Activity,
//    context: Context,
//    onAddDoc: () -> Unit,
//    onAddFromGallery: () -> Unit,
//    onAddFromCamera: () -> Unit,
//    onCancel: () -> Unit,
//    onSystemClick: () -> Unit
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_attachment_with_system, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setCancelable(true)
//    val window = dDialog.window
//    val wlp = window!!.attributes
//    wlp.gravity = Gravity.BOTTOM
//    wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    window.attributes = wlp
//    dialogView.upload_from_gallery.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromGallery.invoke()
//    }
//
//    dialogView.upload_from_docs.setOnClickListener {
//        dDialog.dismiss()
//        onAddDoc.invoke()
//    }
//
//    dialogView.upload_photo.setOnClickListener {
//        dDialog.dismiss()
//        onAddFromCamera.invoke()
//    }
//    dialogView.upload_from_system.setOnClickListener {
//        dDialog.dismiss()
//        onSystemClick.invoke()
//    }
//    dialogView.button_cancel_download.setOnClickListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//
//    dDialog.setOnCancelListener {
//        dDialog.dismiss()
//        onCancel.invoke()
//    }
//    dDialog.show()
//    return dDialog
//}
//fun onShowImageFromPhone(
//    activity: Activity,
//    context: Context,
//    attachment: String
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_show_image, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setOnShowListener {
//        Glide.with(context)
//            .load(attachment)
//            .into(dialogView.imageView)
//    }
//    dDialog.setCancelable(true)
//    dDialog.show()
//    return dDialog
//}
//
//fun onShowImageFromSrc(
//    activity: Activity,
//    context: Context,
//    src: String
//): AlertDialog {
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.dialog_show_image, null)
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dDialog.setOnShowListener {
//        Glide.with(context)
//            .load(src)
//            .into(dialogView.imageView)
//    }
//    dDialog.setCancelable(true)
//    dDialog.show()
//    return dDialog
//}
//
//fun dialogCalendarPeriod(
//    activity: Activity,
//    context: Context,
//    onFinish: (dateStr: String) -> Unit
//): AlertDialog {
//    var sdf = SimpleDateFormat("dd.MM.yyyy")
//    var dateStart: Date? = null
//    var dateEnd: Date? = null
//    var currentDate: Date = Date()
//    var dateStr = ""
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.calendar_filter_period, null)
//    val calendar = dialogView.compactcalendar_view
//    dialogBuilder.setView(dialogView)
//    var activeButton = 0
//    val dDialog = dialogBuilder.create()
//    val days = arrayOf(
//        "Пн",
//        "Вт",
//        "Ср",
//        "Чт",
//        "Пт",
//        "Сб",
//        "Вс"
//    )
//    calendar.setDayColumnNames(days)
//    calendar.setCurrentDate(Date())
//    dialogView.month_god.text =
//        convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    calendar.shouldDrawIndicatorsBelowSelectedDays(false)
//    dialogView.back_calendar.setOnClickListener{
//        calendar.scrollLeft()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    dialogView.forward_calendar.setOnClickListener{
//        calendar.scrollRight()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    dialogView.period_start_button.setTextColor(context.getColor(R.color.colorWhite))
//    dialogView.period_end_button.setTextColor(context.getColor(R.color.colorGrey))
//    dialogView.period_start_button.background =
//        context.getDrawable(R.drawable.frame_button_calendar_period_active)
//    dialogView.period_end_button.background =
//        context.getDrawable(android.R.color.transparent)
//    activeButton = 0
//    calendar.setListener(object : CompactCalendarView.CompactCalendarViewListener {
//        override fun onMonthScroll(firstDayOfNewMonth: Date?) {
//            dialogView.month_god.text =
//                convertLongToTitle(
//                    convertLongToTime(
//                        calendar.firstDayOfCurrentMonth.time
//                    )
//                )
//        }
//
//        override fun onDayClick(dateClicked: Date?) {
//            when (activeButton) {
//                0 -> {
//                    dateStart = dateClicked!!
//                    dialogView.period_start_button.text =
//                        sdf.format(dateStart!!)
//                }
//                1 -> {
//                    dateEnd = dateClicked!!
//                    dialogView.period_end_button.text =
//                        sdf.format(dateEnd!!)
//                }
//            }
//
//            currentDate = dateClicked!!
//        }
//
//    })
//    dialogView.button_ok.setOnClickListener {
//
//        if (dateStart != null && dateEnd != null && dateStart!! > dateEnd!!) {
//            val temp = dateStart
//            dateStart = dateEnd
//            dateEnd = temp
//            dateStr =
//                sdf.format(dateStart!!) + " - " + sdf.format(dateEnd!!)
//        }
//        dateStr = if (dateStart == null && dateEnd == null) {
//            sdf.format(Date())
//        } else if (dateStart == null) {
//            "До " + sdf.format(dateEnd!!)
//        } else if (dateEnd == null) {
//            "С " + sdf.format(dateStart!!)
//        } else {
//            sdf.format(dateStart!!) + " - " + sdf.format(dateEnd!!)
//        }
//
//        onFinish.invoke(dateStr)
//        dateEnd = null
//        dateStart = null
//        dDialog.dismiss()
//    }
//    dialogView.button_cancel.setOnClickListener {
//        dateEnd = null
//        dateStart = null
//        dDialog.dismiss()
//    }
//    dialogView.period_start_button.setOnClickListener {
//        dialogView.period_start_button.setTextColor(context.getColor(R.color.colorWhite))
//        dialogView.period_end_button.setTextColor(context.getColor(R.color.colorGrey))
//        dialogView.period_start_button.background =
//            context.getDrawable(R.drawable.frame_button_calendar_period_active)
//        dialogView.period_end_button.background =
//            context.getDrawable(android.R.color.transparent)
//        activeButton = 0
//        if (dateStart != null) {
//            calendar.setCurrentDate(dateStart)
//        }
//
//    }
//    dialogView.period_end_button.setOnClickListener {
//        dialogView.period_start_button.setTextColor(context.getColor(R.color.colorGrey))
//        dialogView.period_end_button.setTextColor(context.getColor(R.color.colorWhite))
//        dialogView.period_start_button.background =
//            context.getDrawable(android.R.color.transparent)
//        dialogView.period_end_button.background =
//            context.getDrawable(R.drawable.frame_button_calendar_period_active)
//        activeButton = 1
//        if (dateEnd != null) {
//            calendar.setCurrentDate(dateEnd)
//        }
//    }
//    dialogView.check_date_calendar.setOnClickListener {
//        dDialog.dismiss()
//        dateEnd = null
//        dateStart = null
//        dialogCalendarDate(activity, context, onFinish)
//    }
//    dialogView.button_discard.setOnClickListener {
//        currentDate = Date()
//        dateEnd = null
//        dateStart = null
//        dDialog.dismiss()
//        dialogCalendarPeriod(activity, context, onFinish)
//    }
//    dDialog.show()
//    return dDialog
//
//}
//
//fun dialogCalendarDate(
//    activity: Activity,
//    context: Context,
//    onFinish: (dateStr: String) -> Unit
//): AlertDialog {
//    var currentDate: Date = Date()
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.calendar_filter_date, null)
//    val calendar = dialogView.compactcalendar_view
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    val days = arrayOf(
//        "Пн",
//        "Вт",
//        "Ср",
//        "Чт",
//        "Пт",
//        "Сб",
//        "Вс"
//    )
//    calendar.setDayColumnNames(days)
//    calendar.setCurrentDate(Date())
//    dialogView.month_god.text =
//        convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    calendar.shouldDrawIndicatorsBelowSelectedDays(false)
//    dialogView.back_calendar.setOnClickListener {
//        calendar.scrollLeft()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    dialogView.forward_calendar.setOnClickListener {
//        calendar.scrollRight()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    calendar.setListener(object : CompactCalendarView.CompactCalendarViewListener {
//        override fun onMonthScroll(firstDayOfNewMonth: Date?) {
//            dialogView.month_god.text =
//                convertLongToTitle(
//                    convertLongToTime(
//                        calendar.firstDayOfCurrentMonth.time
//                    )
//                )
//        }
//
//        override fun onDayClick(dateClicked: Date?) {
//            currentDate = dateClicked!!
//        }
//
//    })
//
//    dialogView.check_date_calendar.setOnClickListener {
//        dDialog.dismiss()
//        dialogCalendarPeriod(activity, context, onFinish)
//    }
//    dialogView.button_ok.setOnClickListener {
//        var dateStr = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
//        onFinish.invoke(dateStr)
//        dDialog.dismiss()
//    }
//    dialogView.button_cancel.setOnClickListener {
//        dDialog.dismiss()
//    }
//    dialogView.button_discard.setOnClickListener {
//        currentDate = Date()
//        dDialog.dismiss()
//        dialogCalendarDate(activity, context, onFinish)
//    }
//    dDialog.show()
//    return dDialog
//}
//
//fun dialogCalendarSingleDate(
//    activity: Activity,
//    context: Context,
//    onFinish: (dateStr: String) -> Unit
//): AlertDialog {
//    var currentDate: Date = Date()
//    val dialogBuilder = AlertDialog.Builder(context)
//    val inflater = activity.layoutInflater
//    val dialogView = inflater.inflate(R.layout.calendar_filter_date, null)
//    val calendar = dialogView.compactcalendar_view
//    dialogBuilder.setView(dialogView)
//    val dDialog = dialogBuilder.create()
//    dialogView.check_date_calendar.gone()
//    dialogView.select_date_calendar.margin(0f)
//    var layout: ConstraintLayout.LayoutParams =
//        dialogView.select_date_calendar.layoutParams as ConstraintLayout.LayoutParams
//    layout.endToEnd = dialogView.calendar_check_mode.id
//    layout.startToStart = dialogView.calendar_check_mode.id
//    dialogView.select_date_calendar.layoutParams = layout
//    val days = arrayOf(
//        "Пн",
//        "Вт",
//        "Ср",
//        "Чт",
//        "Пт",
//        "Сб",
//        "Вс"
//    )
//    calendar.setDayColumnNames(days)
//    calendar.setCurrentDate(Date())
//    dialogView.month_god.text =
//        convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    calendar.shouldDrawIndicatorsBelowSelectedDays(false)
//    dialogView.back_calendar.setOnClickListener {
//        calendar.scrollLeft()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    dialogView.forward_calendar.setOnClickListener {
//        calendar.scrollRight()
//        dialogView.month_god.text =
//            convertLongToTitle(convertLongToTime(calendar.firstDayOfCurrentMonth.time))
//    }
//    calendar.setListener(object : CompactCalendarView.CompactCalendarViewListener {
//        override fun onMonthScroll(firstDayOfNewMonth: Date?) {
//            dialogView.month_god.text =
//                convertLongToTitle(
//                    convertLongToTime(
//                        calendar.firstDayOfCurrentMonth.time
//                    )
//                )
//        }
//
//        override fun onDayClick(dateClicked: Date?) {
//            currentDate = dateClicked!!
//        }
//    })
//
//    dialogView.check_date_calendar.setOnClickListener {
//        dDialog.dismiss()
//        dialogCalendarPeriod(activity, context, onFinish)
//    }
//    dialogView.button_ok.setOnClickListener {
//        var dateStr = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
//        onFinish.invoke(dateStr)
//        dDialog.dismiss()
//    }
//    dialogView.button_cancel.setOnClickListener {
//        dDialog.dismiss()
//    }
//    dialogView.button_discard.setOnClickListener {
//        currentDate = Date()
//        dDialog.dismiss()
//        dialogCalendarDate(activity, context, onFinish)
//    }
//    dDialog.show()
//    return dDialog
//}
//
//fun timePickerDialog(
//    activity: Activity,
//    context: Context,
//    onSetTime: (selectedHour : Int , selectedMinute : Int) -> Unit
//): TimePickerDialog {
//    var hour = 0
//    var minute = 0
//    val dialogStyle = android.R.style.Theme_Holo_Light_Dialog_NoActionBar
//    val dialogBuilder = TimePickerDialog(context,
//        dialogStyle,
//        { timePicker, sHour, sMinute ->
//            hour = sHour
//            minute = sMinute
//            onSetTime.invoke(hour, minute)
//        },
//        0,
//        0,
//        true
//    )
//    dialogBuilder.window!!.setBackgroundDrawableResource(android.R.color.transparent)
//    dialogBuilder.show()
//    return dialogBuilder
//}