/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import PurchaseService from '@/entities/purchase/purchase.service';
import { Purchase } from '@/shared/model/purchase.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Purchase Service', () => {
    let service: PurchaseService;
    let elemDefault;

    beforeEach(() => {
      service = new PurchaseService();
      elemDefault = new Purchase(0, 'AAAAAAA', 'AAAAAAA', 0, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Purchase', async () => {
        const returnedFromService = Object.assign(
          {
            userName: 'BBBBBB',
            userAddress: 'BBBBBB',
            quantity: 1,
            confirmacao: true,
            withCoupon: true,
            withWarranty: true,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Purchase', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
