package jp.saori.hitandblow.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.saori.hitandblow.model.GameModel;
import jp.saori.hitandblow.model.TableModel;

@Controller
@SessionAttributes("gameModel")
public class GameController {

	@ModelAttribute("gameModel")
	public GameModel setupGameModel() {
		return new GameModel();
	}

	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public String toGame(Model model) {
		return "gameTop";
	}

	@RequestMapping(value = "/top", method = RequestMethod.POST, params = "3")
	public String digitSet3(@ModelAttribute GameModel gameModel, Model model) {
		gameModel.setDigit(3);
		return "redirect:/play";
	}

	@RequestMapping(value = "/top", method = RequestMethod.POST, params = "4")
	public String digitSet4(@ModelAttribute GameModel gameModel, Model model) {
		gameModel.setDigit(4);
		return "redirect:/play";
	}

	@RequestMapping(value = "/top", method = RequestMethod.POST, params = "5")
	public String digitSet5(@ModelAttribute GameModel gameModel, Model model) {
		gameModel.setDigit(5);
		return "redirect:/play";
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String toStart(@ModelAttribute GameModel gameModel, Model model) {
		//桁数を取得
		int digitNum = gameModel.getDigit();
		if (digitNum == 0) {
			return "redirect:/top";
		}
		//0から9までの数字をArrayListに追加
		List<Integer> numberList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numberList.add(i);
		}
		//ArrayListをシャッフルして桁数分取り出し、answer配列に入れる
		Collections.shuffle(numberList);
		int[] answer = new int[digitNum];
		System.out.print("answer : ");
		for (int i = 0; i < digitNum; i++) {
			answer[i] = numberList.get(i);
			System.out.print(answer[i]);
		}
		System.out.println();

		List<TableModel> tableList = new ArrayList<TableModel>();

		gameModel.setCount(0);
		gameModel.setAnswer(answer);
		gameModel.setTableList(tableList);

		return "gamePlay";
	}

	@RequestMapping(value = "/play", method = RequestMethod.POST, params = "challenge")
	public String gamePlay(@Validated @ModelAttribute GameModel gameModel, BindingResult result, Model model){
		//未入力の場合
		if (result.hasErrors()) {
			return "gamePlay";
		}

		//Play桁数を取得
		int digitNum = gameModel.getDigit();

		//入力した数値を取得し、1桁ずつに分けてchar配列に変換
		char[] charArray = gameModel.getNumber().toCharArray();

		//charArrayをint型に変換して格納する配列を生成
		int[] numberArray = new int[digitNum];
		for (int i = 0; i < digitNum; i++) {
			numberArray[i] = Character.getNumericValue(charArray[i]);

			//入力された値が数値ではなかった場合
			if (numberArray[i] < 0 || numberArray[i] > 9) {
				model.addAttribute("errorMessage", "数字以外は入力できません");
				return "gamePlay";
			}
		}

		//入力された桁数と答えの桁数が異なる場合
		if (charArray.length != digitNum) {
			model.addAttribute("errorMessage", "桁数が異なっています");
			return "gamePlay";
		}

		//入力された数値に重複がある場合
		for (int i = 0; i < digitNum - 1 ; i++) {
			for(int j = i+1; j < digitNum; j++) {
				if(numberArray[i] == numberArray[j]) {
					model.addAttribute("errorMessage", "数字が重複しています");
					return "gamePlay";
				}
			}
		}

		List<TableModel> tableList = gameModel.getTableList();

		int count = gameModel.getCount();
		count++;
		int hit = 0;
		int blow = 0;

		for (int i = 0; i < digitNum; i++) {
			for(int j = 0; j < digitNum; j++) {
				if (numberArray[i] == gameModel.getAnswer(j)) {
					if(i == j) {
						hit++;
					} else {
						blow++;
					}
				}
			}
		}

		gameModel.setCount(count);

		TableModel tableModel = new TableModel();
		tableModel.setCount(gameModel.getCount());
		tableModel.setNumber(gameModel.getNumber());
		tableModel.setHit(hit);
		tableModel.setBlow(blow);

		tableList.add(tableModel);

		model.addAttribute("tableList", tableList);

		if (hit == digitNum) {
			model.addAttribute("message1", "おめでとう");
			model.addAttribute("message2",+ count + "回目で正解しました");
			return "gameFinish";
		}

		return "gamePlay";
	}

	@RequestMapping(value = "/play", method = RequestMethod.POST, params = "giveup")
	public String giveUp(@ModelAttribute GameModel gameModel, Model model) {
		List<TableModel> tableList = gameModel.getTableList();
		model.addAttribute("message1",  "残念");
		model.addAttribute("tableList", tableList);
		return "gameFinish";
	}

	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String gameFinish(@ModelAttribute GameModel gameModel, Model model, SessionStatus status) {
		status.setComplete();
		return "redirect:/top";
	}

}