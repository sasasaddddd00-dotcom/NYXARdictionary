package com.nyxar.dictionary.ui
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nyxar.dictionary.data.*
import com.nyxar.dictionary.databinding.FragmentDetailBinding
import kotlinx.coroutines.*

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val db = NyxarDatabase.get(requireContext())
        val id = arguments?.getLong("wordId") ?: 0L

        MainScope().launch {
            val word = db.dao().getById(id)
            binding.etNyxar.setText(word.nyxar)
            binding.etJp.setText(word.japanese)
        }

        binding.btnSave.setOnClickListener {
            if (binding.etAdminKey.text.toString() == "NYXAR-MASTER-2026") {
                // 保存ロジック
                Toast.makeText(context, "更新完了", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "キーが違います", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}