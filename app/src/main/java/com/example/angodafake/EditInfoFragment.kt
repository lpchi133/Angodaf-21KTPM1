package com.example.angodafake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [EditInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditInfoFragment(private var idUser: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private val checkArray = BooleanArray(7) { false } //kiem tra tinh hop le thong tin nguoi dung nhap
    private lateinit var lName : TextInputLayout
    private lateinit var etName : TextInputEditText
    private lateinit var lDob : TextInputLayout
    private lateinit var etDob : TextInputEditText
    private lateinit var rgGender : RadioGroup
    private lateinit var lCountry : TextInputLayout
    private lateinit var actvCountry : MaterialAutoCompleteTextView
    private lateinit var lEmail : TextInputLayout
    private lateinit var etEmail : TextInputEditText
    private lateinit var lPhoneN : TextInputLayout
    private lateinit var etPhoneN : TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_info, container, false)
        initUI(view)
        return view
    }

    private fun initUI(view: View){
        lName = view.findViewById(R.id.lName)
        etName = lName.editText as TextInputEditText
        lDob = view.findViewById(R.id.lDob)
        etDob = lDob.editText as TextInputEditText
        rgGender = view.findViewById(R.id.radioGroup)
        lCountry = view.findViewById(R.id.lCountry)
        actvCountry = view.findViewById(R.id.actvCountry)
        lEmail = view.findViewById(R.id.lEmail)
        etEmail = lEmail.editText as TextInputEditText
        lPhoneN = view.findViewById(R.id.lPhoneN)
        etPhoneN = lPhoneN.editText as TextInputEditText
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idUser: String) =
            EditInfoFragment(idUser).apply {
                arguments = Bundle().apply {
                }
            }
    }
}