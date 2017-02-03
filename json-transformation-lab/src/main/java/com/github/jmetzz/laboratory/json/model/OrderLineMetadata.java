package com.github.jmetzz.laboratory.json.model;

import com.fasterxml.jackson.databind.JsonNode;

public class OrderLineMetadata {

	private JsonNode product;
	private JsonNode packaging;

	public JsonNode getProduct() {
		return product;
	}

	public void setProduct(JsonNode product) {
		this.product = product;
	}

	public JsonNode getPackaging() {
		return packaging;
	}

	public void setPackaging(JsonNode packaging) {
		this.packaging = packaging;
	}

	public static class OrderLineMetaDataBuilder {

		private JsonNode product;
		private JsonNode packaging;

		public OrderLineMetaDataBuilder product(JsonNode value) {
			this.product = value;
			return this;
		}

		public OrderLineMetaDataBuilder packaging(JsonNode value) {
			this.packaging = value;
			return this;
		}

		public OrderLineMetadata build() {
			OrderLineMetadata result = new OrderLineMetadata();

			result.setProduct(product);
			result.setPackaging(packaging);
			return result;
		}
	}

	public static OrderLineMetaDataBuilder newBuilder() {
		return new OrderLineMetaDataBuilder();
	}

	public static OrderLineMetaDataBuilder buildFrom(OrderLineMetadata original) {
		OrderLineMetaDataBuilder builder = newBuilder();
		builder.product(original.getProduct());
		builder.packaging(original.getPackaging());
		return builder;
	}

}
