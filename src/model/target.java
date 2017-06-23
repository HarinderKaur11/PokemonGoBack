package model;

import controller.GameController;

public enum target {
	
	youractive{
			public Object getTarget(){
				return getPlayer().getActivePokemon();
			}
			public Player getPlayer(){
				return Turn.getInstance().getCurrentPlayer();
			}
		},
	opponentactive{
			public Player getPlayer(){
				return Turn.getInstance().getOpponent();
			}
			public Object getTarget(){
				return getPlayer().getActivePokemon();
			}
		},
	choiceopponent{
			public Player getPlayer(){
				return Turn.getInstance().getOpponent();
			}
			public Object getTarget(){
				return GameController.getInstance().getHandandBenchPokemonsDialog(getPlayer());
		}
	},
	choiceyour{
		public Player getPlayer(){
			return Turn.getInstance().getCurrentPlayer();
		}
		public Object getTarget(){
			return GameController.getInstance().getHandandBenchPokemonsDialog(getPlayer());
		}
	},
	opponentbench{
		public Player getPlayer(){
			return Turn.getInstance().getOpponent();
		}
		public Object getTarget(){
			return GameController.getInstance().getPanelPokemonDialog(getPlayer(), "bench");
		}
	},
	yourhand{
		public Player getPlayer(){
			return Turn.getInstance().getCurrentPlayer();
		}
		public Object getTarget(){
			return getPlayer().getInhand();
		}
	},
	opponenthand{
		public Player getPlayer(){
			return Turn.getInstance().getOpponent();
		}
		public Object getTarget(){
			return getPlayer().getInhand();
		}
	},
	yourbench{
		public Player getPlayer(){
			return Turn.getInstance().getCurrentPlayer();
		}
		public Object getTarget(){
			return GameController.getInstance().getPanelPokemonDialog(getPlayer(), "bench");
		}
	},
	your{
		public Object getTarget() {
			return Turn.getInstance().getCurrentPlayer();
		}

		public Player getPlayer() {
			return Turn.getInstance().getCurrentPlayer();
		}
	},
	you{
		public Object getTarget() {
			return Turn.getInstance().getCurrentPlayer();
		}

		public Player getPlayer() {
			return Turn.getInstance().getCurrentPlayer();
		}
	},
	opponent{
		public Object getTarget() {
			return Turn.getInstance().getOpponent();
		}

		public Player getPlayer() {
			return Turn.getInstance().getOpponent();
		}
	};
	
	target(){}
	
	public abstract Object getTarget();
		
	public static target getTargetObject(String name){
		for(target t : target.values()){
			if(t.name().equals(name)){
				return t;
			}
		}
		return null;
	}
	
	public abstract Player getPlayer();

	
//	public static void main(String[] arg){
//		AIplayer ai = new AIplayer("F");
//		UserPlayer user= new UserPlayer("");
//		ai.setTurn(false);
//		user.setTurn(true);
//		Turn.getInstance().setPlayer(ai,user);
//		Debug.message("1");
//		System.out.print(target.getTargetObject("your").getTarget().getName());
//	}
}
