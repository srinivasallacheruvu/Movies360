package com.movie360.dto;

import java.io.Serializable;

public class Sections  implements Serializable {
		private  String sectionName;
		private boolean show;
		public String getSectionName() {
			return sectionName;
		}
		public void setSectionName(String sectionName) {
			this.sectionName = sectionName;
		}
		public boolean isShow() {
			return show;
		}
		public void setShow(boolean show) {
			this.show = show;
		}
		
}
