package application;

public class playerBags {
	
	int Item_Id_0;
	int Item_Id_0_Usage;
	
	int Item_Id_1;
	int Item_Id_1_Usage;
	
	int Item_Id_2;
	int Item_Id_2_Usage;
	
	int Item_Id_3;
	int Item_Id_3_Usage;
	
	int Item_Id_4;
	int Item_Id_4_Usage;
	
	public playerBags(int Item_Id_0, int Item_Id_0_Usage, int Item_Id_1, int Item_Id_1_Usage, int Item_Id_2, int Item_Id_2_Usage, int Item_Id_3, int Item_Id_3_Usage, int Item_Id_4, int Item_Id_4_Usage) {
		
		this.Item_Id_0 = Item_Id_0;
		this.Item_Id_0_Usage = Item_Id_0_Usage;
		
		this.Item_Id_1 = Item_Id_1;
		this.Item_Id_1_Usage = Item_Id_1_Usage;
		
		this.Item_Id_2 = Item_Id_2;
		this.Item_Id_2_Usage = Item_Id_2_Usage;
		
		this.Item_Id_3 = Item_Id_3;
		this.Item_Id_3_Usage = Item_Id_3_Usage;
		
		this.Item_Id_4 = Item_Id_4;
		this.Item_Id_4_Usage = Item_Id_4_Usage;

	}
	
	public int getItem_Id(int slot) {
		
		switch (slot) {
			case 0:
				return Item_Id_0;
				
			case 1:
				return Item_Id_1;
				
			case 2:
				return Item_Id_2;
				
			case 3:
				return Item_Id_3;
				
			case 4:
				return Item_Id_4;		
		}
		return 0;
	}
	
	public int getItem_Id_Usage(int slot) {
			
			switch (slot) {
				case 0:
					return Item_Id_0_Usage;
					
				case 1:
					return Item_Id_1_Usage;
					
				case 2:
					return Item_Id_2_Usage;
					
				case 3:
					return Item_Id_3_Usage;
					
				case 4:
					return Item_Id_4_Usage;		
			}
		return 0;
	}
	
	
}

