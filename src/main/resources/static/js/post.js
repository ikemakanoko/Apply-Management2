// ページ読込完了後にボタンにclickイベントを登録する
document.addEventListener("DOMContentLoaded", () => {
    const openButtons = document.querySelectorAll(".open-modal");
    const closeButtons = document.querySelectorAll(".close-button");

    openButtons.forEach(button => {
        button.addEventListener("click", event => {
            event.preventDefault();
            const modalId = button.getAttribute("data-modal-id");
            document.getElementById(`modal-${modalId}`).style.display = "block";
        });
    });

    closeButtons.forEach(button => {
        button.addEventListener("click", event => {
            const modalId = button.getAttribute("data-modal-id");
            document.getElementById(`modal-${modalId}`).style.display = "none";
        });
    });

    window.addEventListener("click", event => {
        if (event.target.classList.contains("modal")) {
            event.target.style.display = "none";
        }
    });
});
window.addEventListener("load", function() {
	document.getElementById("send_mixdata").addEventListener("click", function() {
		// FoemDataオブジェクトに要素セレクタを渡して宣言する
		var formDatas = document.getElementById("userinfo2");
		var mixedDatas = new FormData(formDatas);

		// appendメソッドでキーとデータの組をセットする
		// append("キー(FORMで云うところのname属性値)",  "データ")でデータをセットできる
		// appendではデータは追加となる
		mixedDatas.append("filename", "test.txt");
		mixedDatas.append("filesize", "10,154B");

		// XHRの宣言
		var XHR = new XMLHttpRequest();

		// openメソッドにPOSTを指定して送信先のURLを指定します
		XHR.open("POST", "./hoge/fuga.php", true);

		// sendメソッドにデータを渡して送信を実行する
		XHR.send(mixedDatas);

		// サーバの応答をonreadystatechangeイベントで検出して正常終了したらデータを取得する
		XHR.onreadystatechange = function() {
			if (XHR.readyState == 4 && XHR.status == 200) {
				// POST送信した結果を表示する
				document.getElementById("mixdata_response").innerHTML = XHR.responseText;
			}
		};
	}, false);
}, false);