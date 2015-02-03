package com.degloba.persistence.test.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.degloba.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "dictionaries")
@NamedQueries({
    @NamedQuery(name = "Dictionay.findByCategory", query = "select o from Dictionary as o where o.disabled = false and o.category = :category order by o.sortOrder"),
    @NamedQuery(name = "Dictionay.findByCategoryArrayParams", query = "select o from Dictionary as o where o.disabled = false and o.category = ? order by o.sortOrder"),
    @NamedQuery(name = "Dictionay.findByCategoryAndCode", query = "select o from Dictionary as o where o.disabled = false and o.category = ? and o.code = ?"),
    @NamedQuery(name = "Dictionay.updateDescription", query = "update Dictionary set description = :description where category = :category"),
    @NamedQuery(name = "Dictionay.findNameAndOrder", query = "select o.code, o.text from  Dictionary o where o.category = :category")})
public class Dictionary extends AbstractEntity {

    private static final long serialVersionUID = 5429887402331650527L;

    @Size(min = 1)
    private String code;

    @Size(min = 1)
    private String text;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private DictionaryCategory category;

    @Column(name = "sort_order")
    private int sortOrder;

    @Column(name = "is_disabled")
    private boolean disabled = false;

    private String description;

    @Column(name = "parent_code")
    private String parentCode;

    public Dictionary() {
    }

    public Dictionary(String code, String text, DictionaryCategory category) {
        this.code = code;
        this.text = text;
        this.category = category;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the category
     */
    public DictionaryCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(DictionaryCategory category) {
        this.category = category;
    }

    /**
     * @return the sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder the sortOrder to set
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode the parentCode to set
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void disable() {
        setDisabled(true);
        save();
    }

    public static Dictionary get(Long id) {
        return getRepository().get(Dictionary.class, id);
    }

    public static Dictionary load(Long id) {
        return getRepository().load(Dictionary.class, id);
    }

    public static List<Dictionary> findByCategory(DictionaryCategory category) {
        return getRepository().createNamedQuery("Dictionay.findByCategory").addParameter("category", category).list();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("text", text).toString();
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"code", "category"};
    }

	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

}
