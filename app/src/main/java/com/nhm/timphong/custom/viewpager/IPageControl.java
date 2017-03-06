package com.nhm.timphong.custom.viewpager;

//PageControl �⺻Ʋ
public interface IPageControl {
	/**
	 * �������� ��ü ũ�⸦ �����մϴ�.
	 * @param size
	 */
	public void setPageSize(int size);
	/**
	 * ������ ��ü ũ�⸦ �����մϴ�.
	 * @return
	 */
	public int getPageSize();
	
	/**
	 * �������� ȣ���մϴ�.
	 * @param index
	 */
	public void setPageIndex(int index);
	/**
	 * ���� �������� �����մϴ�.
	 * @return
	 */
	public int getCurrentPageIndex(); 
}
