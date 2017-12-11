package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data;

import java.util.List;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public class DocumentData {

    private boolean success;
    private String message;
	private List<DocumentDetails> subscheme_document_list;

	public DocumentData(boolean success, String message, List<DocumentDetails> subscheme_document_list) {
		this.success = success;
		this.message = message;
		this.subscheme_document_list = subscheme_document_list;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public List<DocumentDetails> getSubscheme_document_list() {
		return subscheme_document_list;
	}
}
