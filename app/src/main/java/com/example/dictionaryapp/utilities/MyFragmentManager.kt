package com.example.dictionaryapp.utilities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.dictionaryapp.R

object MyFragmentManager {
    var fTransaction: FragmentTransaction? = null

    @JvmStatic
    fun callFragment(fm: FragmentManager,obj: Fragment,resId: Int,extras: Bundle?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit)
            fTransaction!!.replace(resId,obj)
            fTransaction!!.addToBackStack(null)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

    fun callFragmentWithTag(fm: FragmentManager,obj: Fragment,resId: Int,extras: Bundle?,tag: String?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit)
            fTransaction!!.replace(resId,obj,"Payee")
            fTransaction!!.addToBackStack(null)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

    fun callFragmentWithoutAnimation(fm: FragmentManager,obj: Fragment,resId: Int,extras: Bundle?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.replace(resId,obj)
            fTransaction!!.addToBackStack(null)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

    fun callFragmentWithoutStack(fm: FragmentManager,obj: Fragment,resId: Int,extras: Bundle?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit)
            fTransaction!!.replace(resId,obj)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

    fun callAddFragmentWithStack(fm: FragmentManager,obj: Fragment,resId: Int,extras: Bundle?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.add(resId,obj)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }

    fun popAllFragment(fragmentManager: FragmentManager) {
        try {
            fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (e: Exception) {
        }
    }

    fun popFragment(fragmentManager: FragmentManager) {
        try {
            fragmentManager.popBackStackImmediate()
        } catch (e: Exception) {
        }
    }

    fun callAddFragmentWithStackHide(fm: FragmentManager,currObj: Fragment,obj: Fragment,resId: Int,extras: Bundle?) {
        try {
            try {
                if (extras != null) {
                    obj.arguments = extras
                }
            } catch (e: Exception) {
            }
            fTransaction = fm.beginTransaction()
            fTransaction!!.add(resId,obj)
            fTransaction!!.hide(currObj)
            fTransaction!!.addToBackStack(currObj.javaClass.name)
            fTransaction!!.commitAllowingStateLoss()
        } catch (e: Exception) {
        }
    }
}