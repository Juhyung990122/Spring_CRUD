package com.springboard.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.springboard.domain.Board;
import com.springboard.response.CommonResult;
import com.springboard.response.ListResult;
import com.springboard.response.SingleResult;

import javassist.expr.Instanceof;

@Service
public class ResponseService {
	public enum CommonResponse{
		SUCCESS(0,"성공"), FAIL(-1,"실패");
		
		int code;
		String msg;
		
		private CommonResponse(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getMsg() {
			return msg;
		}
	}
	//성공 처리 
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}
	//실패 처리 
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}
	
	//성공 데이터 세팅 - 단일, 다중에서 활용하려고 따로 만듬 
	private void setSuccessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
		}
	
	//단일 결과 리턴 
	public <T> SingleResult<T> getSingleResult(T data){
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}
	
	//다중 결과 리턴 
	public <T> ListResult<T> getListResult(List<T> list){
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}

	
	
 	
}
