package br.com.amil.rankingbuilder.api.game;

/**
 * Rrepresenta uma arma utilizada por um {@link Player} para matar
 */
public class Weapon {
	
	private final String name;

	/**
	 * Cria uma nova arma.
	 *
	 * @param name O nome da arma
	 */
	public Weapon(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Recupera o nome da arma
	 *
	 * @return O nome da arma
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
