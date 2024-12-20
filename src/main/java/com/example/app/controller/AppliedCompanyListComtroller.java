package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.AppliedCompanyList;
import com.example.app.domain.StatusList;
import com.example.app.mapper.AppliedCompanyMapper;
import com.example.app.mapper.StatusMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AppliedCompanyListComtroller {

	private final AppliedCompanyMapper appliedCompanyListMapper;
	private final StatusMapper statusListMapper;
	//appliedCompanyListMapperはAppliedCompanyMapperを利用するためのフィールド
	//AppliedCompanyMapperは機能の集合

	//topページ　localhost:8080/top

	//一覧
	@GetMapping("/appliedCompanyList")
	public String list(Model model) {
		// "top" は src/main/resources/templates/top.html に対応
		List<AppliedCompanyList> appliedCompanies = appliedCompanyListMapper.selectAll();
		List<StatusList> statuses = statusListMapper.selectAll();
//		List<StatusList> statuses = statusListMapper.selectAll();
		model.addAttribute("appliedCompanyList", new AppliedCompanyList());
		model.addAttribute("appliedCompanyLists", appliedCompanies);
		model.addAttribute("statuss", statuses);
//		model.addAttribute("statusLists", statuses);
		
		//appliedcompanyテーブルとstatusテーブルの一覧の取得
//		System.out.println(statuses);
		return "appliedCompanyList";
	}

	//詳細
	@GetMapping("/appliedCompanyDetail")
	public String detail(@RequestParam("id") Integer id,
			Model model) {
		AppliedCompanyList company = appliedCompanyListMapper.selectById(id);
		//多分ここで結合したstatusをid,statusidと紐づけて、
		//statusidの番号に対応することば（選考辞退など）を表示させるロジックが必要
		model.addAttribute("appliedCompany", company);
		return "appliedCompanyDetail";
	}

	//新規登録
	@GetMapping("/appliedCompanyInsert")
	public String addGet(Model model) {
//		List<StatusList> statuses = statusListMapper.selectAll();
		model.addAttribute("appliedCompanyList", new AppliedCompanyList());
		//AppliedCompanyListとappliedCompanyListは対応している。
		//appliedCompanyListをHTMLで使う
		//("b", new a());の場合、bはaのキャメルケースでないとうまく動かない
		return "appliedCompanyInsert";
	}

	//エラーハンドリング、バリデーション(新規追加)
	@PostMapping("/appliedCompanyInsert")
	public String addPost(
			@Valid AppliedCompanyList appliedCompanyList,
			Errors errors,
			Model model) {
		System.out.println("エラー数:　" + errors.getErrorCount());

		// エラーの詳細を調べる
		errors.getAllErrors().forEach(System.out::println);

		// エラーがある場合、元のフォームに戻す
		if (errors.hasErrors()) {
			model.addAttribute("appliedCompanyLists", appliedCompanyList); // 入力された値を維持
			System.out.println(errors);
			return "appliedCompanyInsert";
		}
		//System.out.println(appliedCompanyList);
		// バリデーションが通った場合はデータを保存
		appliedCompanyListMapper.add(appliedCompanyList);
		return "insertDone"; // 完了ページに遷移
	}

	//追加完了画面
	@GetMapping("/insertDone")
	public String showinsertDonePage() {
		// deleteCompany2.htmlを返す
		return "insertDone";
	}

	@GetMapping("/deleteCompany2/{id}")
	public String deleteCompany(@PathVariable("id") Integer id,
			Model model
			) {
		model.addAttribute("appliedCompany", new AppliedCompanyList());
		appliedCompanyListMapper.deleteById(id); // idで削除
		// idを使って削除処理を実行
		System.out.println("削除する企業のID: " + id);
		// 必要な削除処理をここで行う
		return "deleteCompany2"; // 削除後のリダイレクト先
	}

	// 編集処理
	@GetMapping("/appliedCompanyEdit/{id}")
	public String updateGet(@PathVariable("id") Integer id, Model model) {
	    AppliedCompanyList appliedCompanyList = appliedCompanyListMapper.selectById(id);
	    StatusList statusLists = statusListMapper.selectById(id);
	    model.addAttribute("appliedCompanyList", appliedCompanyList);
	    model.addAttribute("statusList", statusLists);
	    return "appliedCompanyEdit"; // ビュー名
	    
	}

	@PostMapping("/appliedCompanyEdit/{id}")
	public String editPost(
			@Valid AppliedCompanyList appliedCompanyList,
			Errors errors,
			Model model) {
		System.out.println("エラー数:　" + errors.getErrorCount());
		// エラーの詳細を調べる
		errors.getAllErrors().forEach(System.out::println);
		// エラーがある場合、元のフォームに戻す
		if (errors.hasErrors()) {
			model.addAttribute("appliedCompanyList", appliedCompanyList); // 入力された値を維持
			return "appliedCompanyEdit";
		}
		System.out.println(appliedCompanyList);
		// バリデーションが通った場合はデータを保存
		appliedCompanyListMapper.update(appliedCompanyList);
		return "editedCompany";// 完了ページに遷移
	}
	
	//ステータス変更完了画面表示
		@GetMapping("updateStatus")
		public String statusDetail(@RequestParam("id") Integer id,
				Model model) {
			AppliedCompanyList company = appliedCompanyListMapper.selectById(id);
			//多分ここで結合したstatusをid,statusidと紐づけて、
			//statusidの番号に対応することば（選考辞退など）を表示させるロジックが必要
			model.addAttribute("appliedCompany", company);
			return "updateStatus";
		}
	
	//ステータス変更
		@GetMapping("/updateStatus/{id}")
		public String updateStatusGet(@PathVariable("id") Integer id, Model model) {
		    AppliedCompanyList appliedCompanyList = appliedCompanyListMapper.selectById(id);
		    model.addAttribute("appliedCompanyList", appliedCompanyList);
		    return "updateStatus"; // ビュー名
		    
		}

		@PostMapping("/updateStatus")
		public String updateStatus(
				@Valid StatusList statusList,
				@RequestParam("id") Integer id,
				Model model) {
		    // IDに基づいてステータスを更新
			statusListMapper.updateStatus(statusList);
		    return "redirect:/appliedCompanyList"; // 一覧ページにリダイレクト
		}
}
