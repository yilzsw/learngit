package com.example.mvp.demo.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

object AppManager {
    private val activityStack = Stack<Activity>()

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.push(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return if (activityStack.size == 0) {
            null
        } else activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishCurrentActivity() {
        val activity = activityStack.pop()
        activity.finish()
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activityStack.remove(activity)
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity?.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            manager.killBackgroundProcesses(context.packageName)
            System.exit(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 找到第一个类型匹配的Activity
     *
     * @param activityClass 用于匹配的Activity类
     * @return 如果没找到，返回null
     */
    fun findActivity(activityClass: Class<*>): Activity? {
        var activity: Activity? = null
        for (a in activityStack) {
            if (a != null && a.javaClass == activityClass) {
                activity = a
                break
            }
        }
        return activity
    }


    /**
     * 找到所有类型匹配的Activity
     *
     * @param activityClass 用于匹配的Activity类
     * @return 如果没找到，返回null
     */
    fun findActivityList(activityClass: Class<*>): List<Activity> {
        val activityList = ArrayList<Activity>()
        for (a in activityStack) {
            if (a != null && a.javaClass == activityClass) {
                activityList.add(a)
                continue
            }
        }
        return activityList
    }

    /**
     * 使此activity在堆栈中单例，它会finish之前打开的此activity
     *
     * @param activity 想要作为单例的activity
     */
    fun makeActivityInSingle(activity: Activity) {
        val acts = AppManager.findActivityList(activity.javaClass)
        for (i in 0 until acts.size - 1) {
            val act = acts[i]
            act?.finish()
        }
    }

    fun getStack(): Stack<Activity> {
        return activityStack
    }
}