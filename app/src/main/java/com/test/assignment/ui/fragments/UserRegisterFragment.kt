package com.test.assignment.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.test.assignment.R
import com.test.assignment.databinding.FragmentUserRegisterBinding
import com.test.assignment.utils.SlidingOptions


class UserRegisterFragment : BaseFragment() {
    private var _binding: FragmentUserRegisterBinding? = null
    private val binding get() = _binding!!
    private var listener: UserRegisterFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    fun setListener(listener: UserRegisterFragmentListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserRegisterBinding.inflate(inflater, container, false)
        initView()
        onClicks()

        return binding.root
    }

    private fun onClicks() {
        binding.registerMainView.btnNext.setOnClickListener {
            binding.registerMainView.registerMainView2.visibility = View.VISIBLE
            binding.registerMainView.registerMainView1.visibility = View.GONE
            binding.registerMainView.title.text = getString(R.string.verify_number_title)

            context?.let { it1 -> SlidingOptions.slidRight(binding.registerMainView.registerMainView2, it1) }
            context?.let { it1 -> SlidingOptions.slideLeft(binding.registerMainView.registerMainView1, it1) }
        }

        binding.registerMainView.btnNextVerify.setOnClickListener {
            binding.registerMainView.layersBottomSheetView.showSheetFullView(false, 350 * windowHeight / viewHeight)

            listener?.onMethodCallFromUserRegisterFragment()
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }, 500)
        }
    }

    private fun initView() {
        binding.registerMainView.layersBottomSheetView.showSheetFullView(true, 350 * windowHeight / viewHeight)

        binding.registerMainView.registerMainView1.visibility = View.VISIBLE
        binding.registerMainView.registerMainView2.visibility = View.GONE

        // register view
        setViewMargins(binding.registerMainView.viewContainer, 31 * windowWidth / viewWidth, 0, 31 * windowWidth / viewWidth, 0)
        setViewMargins(binding.registerMainView.registerDescriptionContainer, 0, 23 * windowHeight / viewHeight, 0, 0
        )

        binding.registerMainView.editTextContainerPhoneNumber.layoutParams.height = 46 * windowHeight / viewHeight

        val layoutParams = LinearLayout.LayoutParams(184 * windowWidth / viewWidth, 46 * windowHeight / viewHeight)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.setMargins(0, 48 * windowHeight / viewHeight, 0, 0)
        binding.registerMainView.btnNext.layoutParams = layoutParams

        // verify view
        setViewMargins(binding.registerMainView.verifyDescriptionContainer, 0, 5 * windowHeight / viewHeight, 0, 0)
        setViewMargins(binding.registerMainView.editTextContainerVerifyNumber, 0, 22 * windowHeight / viewHeight, 0, 0)
        setViewMargins(binding.registerMainView.resentCodeContainer, 0, 18 * windowHeight / viewHeight, 0, 0)

        binding.registerMainView.editTextContainerVerifyNumber.layoutParams.height = 46 * windowHeight / viewHeight

        val layoutParamsVerifyBtn = LinearLayout.LayoutParams(184 * windowWidth / viewWidth, 46 * windowHeight / viewHeight)
        layoutParamsVerifyBtn.gravity = Gravity.CENTER
        layoutParamsVerifyBtn.setMargins(0, 41 * windowHeight / viewHeight, 0, 0)
        binding.registerMainView.btnNextVerify.layoutParams = layoutParamsVerifyBtn

        var title = getString(R.string.resend_code)
        var search = "Re-send"

        if (title.toLowerCase().contains(search.toLowerCase())) {
            var spannableString =  SpannableString(title);
            var foregroundColorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.view_pager_select_color));

            spannableString.setSpan(foregroundColorSpan, title.length-7, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.registerMainView.textResendCode.text = spannableString
        }
    }

    interface UserRegisterFragmentListener {
        fun onMethodCallFromUserRegisterFragment()
    }

}